package pe.edu.vallegrande.demo2.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class user {
    private Long id;
    private String nombre;
    private String apellido;
    private String correo;
    private boolean activo;
}
