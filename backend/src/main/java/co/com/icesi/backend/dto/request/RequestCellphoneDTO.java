package co.com.icesi.backend.dto.request;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestCellphoneDTO {
    @NotNull(message = "The description of a cellphone can't be null")
    @NotBlank(message = "The description of a cellphone can't be blank")
    private String description;

    @NotNull(message = "The name of a cellphone can't be null")
    @NotBlank(message = "The name of a cellphone can't be blank")
    private String name;

    @Min(value=0, message = "The price of a new cellphone must be greater than 0")
    @NotNull(message = "The price of a cellphone can't be null")
    @NotBlank(message = "The price of a cellphone can't be blank")
    private long price;

    @NotNull(message = "The image of a cellphone can't be null")
    @NotBlank(message = "The image of a cellphone can't be blank")
    private String imageUrl;

    @NotNull(message = "The brand of a cellphone can't be null")
    @NotBlank(message = "The brand of a cellphone can't be blank")
    private String brand;

    @NotNull(message = "The storage of a cellphone can't be null")
    @NotBlank(message = "The storage of a cellphone can't be blank")
    private String storage;

    @NotNull(message = "The ram of a cellphone can't be null")
    @NotBlank(message = "The ram of a cellphone can't be blank")
    private String ram;

    @NotNull(message = "The operating system of a cellphone can't be null")
    @NotBlank(message = "The operating system of a cellphone can't be blank")
    private String operatingSystem;

    @NotNull(message = "The front camera resolution of a cellphone can't be null")
    @NotBlank(message = "The front camera resolution of a cellphone can't be blank")
    private String frontCameraResolution;

    @NotNull(message = "The main camera resolution of a cellphone can't be null")
    @NotBlank(message = "The main camera resolution of a cellphone can't be blank")
    private String mainCameraResolution;

    @NotNull(message = "The screen size of a cellphone can't be null")
    @NotBlank(message = "The screen size of a cellphone can't be blank")
    private String screenSize;

    @NotNull(message = "The screen size of a cellphone can't be null")
    @NotBlank(message = "The screen size of a cellphone can't be blank")
    private int stock;

    @NotNull(message = "The category of a user can't be null")
    @NotBlank(message = "The category of a user can't be blank")
    private String category;
}
