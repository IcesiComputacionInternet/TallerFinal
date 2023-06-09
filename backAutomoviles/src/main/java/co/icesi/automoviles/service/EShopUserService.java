package co.icesi.automoviles.service;

import java.util.ArrayList;
import java.util.UUID;

import co.icesi.automoviles.dto.EShopUserShowDTO;
import co.icesi.automoviles.dto.EShopUserCreateDTO;
import co.icesi.automoviles.dto.PurchaseOrderShowDTO;
import co.icesi.automoviles.enums.RoleType;
import co.icesi.automoviles.mapper.EShopUserMapper;
import co.icesi.automoviles.model.EShopUser;
import co.icesi.automoviles.model.PurchaseOrder;
import co.icesi.automoviles.service.utils.SortUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.icesi.automoviles.error.exception.DetailBuilder;
import co.icesi.automoviles.error.exception.ErrorCode;
import co.icesi.automoviles.error.util.ShopExceptionBuilder;
import co.icesi.automoviles.model.Role;
import co.icesi.automoviles.repository.EShopUserRepository;
import co.icesi.automoviles.repository.RoleRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EShopUserService {
    private final EShopUserRepository EShopUserRepository;
    private final RoleRepository roleRepository;
    private final EShopUserMapper EShopUserMapper;
    private final PasswordEncoder passwordEncoder;

    public EShopUserShowDTO registerEShopUser(EShopUserCreateDTO EShopUserCreateDTO) {
        checkConditionsToCreateEShopUser(EShopUserCreateDTO);
        Role role = findRoleByName(RoleType.USER.toString());
        EShopUser eShopUser = EShopUserMapper.fromEShopUserCreateDTO(EShopUserCreateDTO);
        eShopUser.setRole(role);
        eShopUser.setEShopUserId(UUID.randomUUID());
        eShopUser.setPassword(passwordEncoder.encode(EShopUserCreateDTO.getPassword()));
        EShopUserShowDTO eShopUserShowDTO = EShopUserMapper.fromEShopUserToEShopUserShowDTO(EShopUserRepository.save(eShopUser));
        eShopUserShowDTO.setEShopUserId(eShopUser.getEShopUserId());
        return eShopUserShowDTO;
    }

    private void checkConditionsToCreateEShopUser(EShopUserCreateDTO EShopUserCreateDTO){
        if(EShopUserCreateDTO.getPhoneNumber() == null && EShopUserCreateDTO.getEmail() == null){
            throw ShopExceptionBuilder.createShopException(
                    "field email and phone number: at least one must be different of null",
                    HttpStatus.BAD_REQUEST,
                    new DetailBuilder(ErrorCode.ERR_400, "email and phone number", "at least one must be different of null")
            ).get();
        }

        ArrayList<String> errors = new ArrayList<>();

        if(!emailAvailable(EShopUserCreateDTO.getEmail())){
            errors.add("There is already a user with the email " + EShopUserCreateDTO.getEmail() + ". ");
        }

        if(!phoneNumberAvailable(EShopUserCreateDTO.getPhoneNumber())){
            errors.add("There is already a user with the phone number " + EShopUserCreateDTO.getPhoneNumber() + ". ");
        }

        if(!errors.isEmpty()){
            String allErrorMessages = errors.stream().reduce("", (errorMessage, error) -> errorMessage + error);
            throw ShopExceptionBuilder.createShopException(
                    allErrorMessages,
                    HttpStatus.BAD_REQUEST,
                    new DetailBuilder(ErrorCode.ERR_400, "email or phone number", allErrorMessages)
            ).get();
        }
    }

    public Page<EShopUserShowDTO> getAllUsers(int page, int perPage, String sortBy, String sortDir){
        Pageable pageable = SortUtil.sort(page, perPage, sortBy, sortDir);
        Page<EShopUser> users = EShopUserRepository.getAllUsers(pageable);
        Page<EShopUserShowDTO> userShowDTOS = users.map(EShopUserMapper::fromEShopUserToEShopUserShowDTO);
        return userShowDTOS;
    }

    private boolean emailAvailable(String email){
        if(email != null && EShopUserRepository.findByEmail(email).isPresent()){
            return false;
        }
        return true;
    }

    private boolean phoneNumberAvailable(String phoneNumber){
        if(phoneNumber != null && EShopUserRepository.findByPhoneNumber(phoneNumber).isPresent()){
            return false;
        }
        return true;
    }

    public Role findRoleByName(String name) {
        return roleRepository.findByRoleName(name)
            .orElseThrow(() -> ShopExceptionBuilder.createShopException(
                "This role is not present in the database",
                HttpStatus.NOT_FOUND,
                new DetailBuilder(ErrorCode.ERR_404, "role", "name", name)
            ).get());
    }

    public EShopUserShowDTO assignRole(String eShopUserId, String roleName) {
        EShopUser eShopUser = findEShopUserById(eShopUserId);
        Role role = findRoleByName(roleName);
        eShopUser.setRole(role);
        EShopUserShowDTO eShopUserShowDTO = EShopUserMapper.fromEShopUserToEShopUserShowDTO(EShopUserRepository.save(eShopUser));
        eShopUserShowDTO.setEShopUserId(eShopUser.getEShopUserId());
        return eShopUserShowDTO;
    } 

    private EShopUser findEShopUserById(String eShopUserId){
        return EShopUserRepository.findById(UUID.fromString(eShopUserId)).orElseThrow(
                ShopExceptionBuilder.createShopException(
                        "There is no e-shop user with the id: " + eShopUserId,
                        HttpStatus.NOT_FOUND,
                        new DetailBuilder(ErrorCode.ERR_404, "e-shop user", "id", eShopUserId))
        );
    }
    
}
