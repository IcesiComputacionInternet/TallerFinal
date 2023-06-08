package co.com.icesi.backend;

import co.com.icesi.backend.Enum.CategoryType;
import co.com.icesi.backend.Enum.UserRole;
import co.com.icesi.backend.model.Category;
import co.com.icesi.backend.model.Cellphone;
import co.com.icesi.backend.model.Role;
import co.com.icesi.backend.model.ShopUser;
import co.com.icesi.backend.repository.CategoryRepository;
import co.com.icesi.backend.repository.CellphoneRepository;
import co.com.icesi.backend.repository.RoleRepository;
import co.com.icesi.backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
	
	@Bean

	CommandLineRunner commandLineRunner(UserRepository userRepository, RoleRepository roleRepository, CategoryRepository categoryRepository, CellphoneRepository cellphoneRepository) {

		Role adminRole = Role.builder()
				.roleId(UUID.randomUUID())
				.roleName(UserRole.ADMIN.getRole())
				.description("Admin role")
				.build();

		Role userRole = Role.builder()
				.roleId(UUID.randomUUID())
				.roleName(UserRole.USER.getRole())
				.description("User role")
				.build();

		Role shopRole = Role.builder()
				.roleId(UUID.randomUUID())
				.roleName(UserRole.SHOP.getRole())
				.description("Shop role")
				.build();

		ShopUser adminShopUser = ShopUser.builder()
				.userId(UUID.randomUUID())
				.firstName("Laura Daniela")
				.lastName("Martinez Ortiz")
				.email("lauramartinez@gmail.com")
				.password("password")
				.phoneNumber("+57317599839")
				.address("Cra 98 #325")
				.birthday(LocalDateTime.of(2002, 6, 14, 0, 0))
				.role(adminRole)
				.build();

		ShopUser user = ShopUser.builder()
				.userId(UUID.randomUUID())
				.firstName("Luis Miguel")
				.lastName("Ossa Arias")
				.email("luismiguel@gmail.com")
				.password("password")
				.phoneNumber("+573175933339")
				.address("Cra 7 #69-64")
				.birthday(LocalDateTime.of(2002, 7, 5, 0, 0))
				.role(userRole)
				.build();

		ShopUser shopUser = ShopUser.builder()
				.userId(UUID.randomUUID())
				.firstName("Keren López")
				.lastName("Córdoba")
				.email("kerenlopez@gmail.com")
				.password("password")
				.phoneNumber("+573166633339")
				.address("Cra 7 #69-64")
				.birthday(LocalDateTime.of(2003, 12, 8, 0, 0))
				.role(shopRole)
				.build();

		Category highRangeCategory = Category.builder()
				.categoryId(UUID.randomUUID())
				.name(CategoryType.HIGH_RANGE.getCategory())
				.description("Category description")
				.build();

		Category midRangeCategory = Category.builder()
				.categoryId(UUID.randomUUID())
				.name(CategoryType.MID_RANGE.getCategory())
				.description("Category description")
				.build();

		Category lowRangeCategory = Category.builder()
				.categoryId(UUID.randomUUID())
				.name(CategoryType.LOW_RANGE.getCategory())
				.description("Category description")
				.build();

		Cellphone item1 = Cellphone.builder()
				.cellphoneId(UUID.randomUUID())
				.name("Xiaomi Celular Redmi Note 12 128Gb Verde")
				.description("Fotografía profesional en tu bolsillo Descubre infinitas posibilidades " +
						"para tus fotos con las 3 cámaras principales de tu equipo. " +
						"Pon a prueba tu creatividad y juega con la iluminación," +
						" diferentes planos y efectos para obtener grandes resultados.")
				.price(929900L)
				.imageUrl("https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcSkEYuXaRsomQIgq1vHTdVXk64aw4_Rw05lfClpIR73BZuCuQllO_CG7ZoDjPWlpU1ItH0iWu0rxUmwymdShHCqw2TsFQSliaFSBTWfX0PSJasSU_VBQGHUAw")
				.brand("Xiaomi")
				.storage("128Gb")
				.ram("8")
				.operatingSystem("Android 12")
				.frontCameraResolution("Single 13 MP")
				.mainCameraResolution("Triple 50 MP + 8 MP + 2MP")
				.screenSize("6.67″ FHD+")
				.stock(15)
				.category(highRangeCategory)
				.build();

		Cellphone item2 = Cellphone.builder()
				.cellphoneId(UUID.randomUUID())
				.name("Celular HUAWEI nova Y70 128 GB Blanco")
				.description("Fotografía profesional en tu bolsillo Descubre infinitas posibilidades " +
						"para tus fotos con las 3 cámaras principales de tu equipo. " +
						"Pon a prueba tu creatividad y juega con la iluminación," +
						" diferentes planos y efectos para obtener grandes resultados.")
				.price(799900L)
				.imageUrl("https://encrypted-tbn0.gstatic.com/shopping?q=tbn:ANd9GcQEY73dgNAtTKbt3yfqN7pwvcVkgqy_Cbs1Ii3Zn5XApXb4tST2x5EI4BDnEYoGf7h9LgxyZ_pR4M9__DQcmVUHwbkHOLZx4osSoFqQ_gEuOH-h43xILhxZ0Q")
				.brand("HUAWEI")
				.storage("128Gb")
				.ram("4")
				.operatingSystem("Armony OS")
				.frontCameraResolution("Single 13 MP")
				.mainCameraResolution("8 Mpx")
				.screenSize("5″")
				.stock(10)
				.category(midRangeCategory)
				.build();

		Cellphone item3 = Cellphone.builder()
				.cellphoneId(UUID.randomUUID())
				.name("Apple iPhone 13 128 GB")
				.description("iPhone 13. Tu nuevo superpoder. ¿Cómo hicimos para ponerle cámaras " +
						"tan poderosas? Pensando en diagonal Diseñamos una arquitectura " +
						"completamente nueva y giramos los lentes 45 grados. ")
				.price(3789000L)
				.imageUrl("https://encrypted-tbn3.gstatic.com/shopping?q=tbn:ANd9GcSxr5PJm5AS9C2BsmZlid_6nF8IYQ4XJZSDRMK-dv-hq7veiHlYDv2RKvdi4iooJRE8ZyRzRq4rIDD4pKfab68faIx9Acj9xMy-Qr7f6q0")
				.brand("Apple")
				.storage("128Gb")
				.ram("4")
				.operatingSystem("iOS 15")
				.frontCameraResolution("Single 13 MP")
				.mainCameraResolution("TrueDepth de 12 MP con modo Noche y grabación de video 4K HDR en Dolby Vision")
				.screenSize("Pantalla Super Retina XDR de 6.1 pulgadas")
				.stock(20)
				.category(highRangeCategory)
				.build();

		Cellphone item4 = Cellphone.builder()
				.cellphoneId(UUID.randomUUID())
				.name("Celular Samsung Galaxy A22 5G 128 GB Gris")
				.description("Fotografía profesional en tu bolsillo Descubre infinitas posibilidades para tus fotos con las 3 " +
						"cámaras principales de tu equipo. Pon a prueba tu creatividad y juega con la " +
						"iluminación, diferentes planos y efectos para obtener grandes resultados.")
				.price(933000L)
				.imageUrl("https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcQtm_jbtdE_kGCSLyd5zDyJ1YTAZ81Wca57Hr-DtQDvZYosnP2bwB-wTtQDt0VmTKsfcmTtIOdzb6YhXA5gCsdGqb0xt8BwuJjel43TA3oYku729UWwxnFyqA")
				.brand("Samsung Galaxy")
				.storage("128Gb")
				.ram("5")
				.operatingSystem("Android")
				.frontCameraResolution("8 Mpx")
				.mainCameraResolution("TrueDepth de 12 MP con modo Noche y grabación de video 4K HDR en Dolby Vision")
				.screenSize("6 pulgadas")
				.stock(20)
				.category(midRangeCategory)
				.build();

		Cellphone item5 = Cellphone.builder()
				.cellphoneId(UUID.randomUUID())
				.name("Celular HONOR X6s 128GB Azul")
				.description("Mayor rendimiento Su memoria RAM de 4 GB permite que tu smartphone funcione de manera fluida " +
						"y sin demoras al realizar distintas tareas, jugar o navegar. Desbloqueo veloz con tu " +
						"huella digital Con el sensor de huella digital")
				.price(619900L)
				.imageUrl("https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcQtm_jbtdE_kGCSLyd5zDyJ1YTAZ81Wca57Hr-DtQDvZYosnP2bwB-wTtQDt0VmTKsfcmTtIOdzb6YhXA5gCsdGqb0xt8BwuJjel43TA3oYku729UWwxnFyqA")
				.brand("HONOR")
				.storage("128Gb")
				.ram("4")
				.operatingSystem("Android")
				.frontCameraResolution("5 Mpx")
				.mainCameraResolution("TrueDepth de 12 MP con modo Noche y grabación de video 4K HDR en Dolby Vision")
				.screenSize("5.5 pulgadas")
				.stock(20)
				.category(midRangeCategory)
				.build();

		Cellphone item6 = Cellphone.builder()
				.cellphoneId(UUID.randomUUID())
				.name("Xiaomi Celular Redmi 10A 32Gb Gris")
				.description("Da vida a tus películas favoritas en la gran pantalla HD+ de 6,53\" de Redmi 10A. " +
						"Gran pantalla de 6,53\" con un diseño moderno. La parte posterior curvada en 3D produce" +
						" una sensación natural en Redmi 10A que otros teléfonos inteligentes")
				.price(485919L)
				.imageUrl("https://encrypted-tbn0.gstatic.com/shopping?q=tbn:ANd9GcSDgOpdRlZX2wGde2hu1CZkvViOWiQp26SB1NC9WP7_DaGIfybjGV-ecOGNB6algpcLlTivruxJMfFhTfC85XmVP_PuU5-Wa8cASUtPJ9AzEAFXi8_92mFq")
				.brand("Xiaomi")
				.storage("32Gb")
				.ram("4")
				.operatingSystem("Android 11")
				.frontCameraResolution("5 Mpx")
				.mainCameraResolution("TrueDepth de 12 MP con modo Noche y grabación de video 4K HDR en Dolby Vision")
				.screenSize("LCD 6,53\" HD+")
				.stock(10)
				.category(midRangeCategory)
				.build();

		return args -> {
			roleRepository.save(adminRole);
			roleRepository.save(userRole);
			roleRepository.save(shopRole);

			userRepository.save(adminShopUser);
			userRepository.save(user);
			userRepository.save(shopUser);

			categoryRepository.save(highRangeCategory);
			categoryRepository.save(midRangeCategory);
			categoryRepository.save(lowRangeCategory);

			cellphoneRepository.save(item1);
			cellphoneRepository.save(item2);
			cellphoneRepository.save(item3);
			cellphoneRepository.save(item4);
			cellphoneRepository.save(item5);
			cellphoneRepository.save(item6);
		};
	}*/
}
