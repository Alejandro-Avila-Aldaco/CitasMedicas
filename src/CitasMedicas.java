import java.util.ArrayList;
import java.util.Scanner;

class Doctor {
    String id;
    String nombre;
    String especialidad;

    Doctor(String id, String nombre, String especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return id + ": " + nombre + " (" + especialidad + ")";
    }
}

class Paciente {
    String id;
    String nombre;

    Paciente(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return id + ": " + nombre;
    }
}

class Cita {
    String id;
    String fechaHora;
    String motivo;
    Doctor doctor;
    Paciente paciente;

    Cita(String id, String fechaHora, String motivo, Doctor doctor, Paciente paciente) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.doctor = doctor;
        this.paciente = paciente;
    }

    @Override
    public String toString() {
        return id + ": " + fechaHora + " - " + motivo + " con " + doctor.nombre + " y " + paciente.nombre;
    }
}

public class CitasMedicas {
    static ArrayList<Doctor> listaDoctores = new ArrayList<>();
    static ArrayList<Paciente> listaPacientes = new ArrayList<>();
    static ArrayList<Cita> listaCitas = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        validarUsuario();
    }

    public static void validarUsuario() {
        System.out.println("Ingrese identificador de usuario:");
        String userId = scanner.nextLine();
        System.out.println("Ingrese contraseña de usuario:");
        String password = scanner.nextLine();

        if (userId.equals("admin") && password.equals("admin123")) {
            mostrarMenu();
        } else {
            System.out.println("Credenciales incorrectas");
            validarUsuario();
        }
    }

    public static void mostrarMenu() {
        while (true) {
            System.out.println("1. Alta de Doctores");
            System.out.println("2. Alta de Pacientes");
            System.out.println("3. Crear Cita");
            System.out.println("4. Listar Citas");
            System.out.println("5. Salir");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea

            switch (opcion) {
                case 1:
                    altaDoctor();
                    break;
                case 2:
                    altaPaciente();
                    break;
                case 3:
                    crearCita();
                    break;
                case 4:
                    listarCitas();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    public static void altaDoctor() {
        System.out.println("Ingrese ID del doctor:");
        String id = scanner.nextLine();
        System.out.println("Ingrese nombre del doctor:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese especialidad del doctor:");
        String especialidad = scanner.nextLine();
        Doctor doctor = new Doctor(id, nombre, especialidad);
        listaDoctores.add(doctor);
        System.out.println("Doctor dado de alta exitosamente");
    }

    public static void altaPaciente() {
        System.out.println("Ingrese ID del paciente:");
        String id = scanner.nextLine();
        System.out.println("Ingrese nombre del paciente:");
        String nombre = scanner.nextLine();
        Paciente paciente = new Paciente(id, nombre);
        listaPacientes.add(paciente);
        System.out.println("Paciente dado de alta exitosamente");
    }

    public static void crearCita() {
        System.out.println("Ingrese ID de la cita:");
        String id = scanner.nextLine();
        System.out.println("Ingrese fecha y hora de la cita (formato: dd/mm/aaaa hh:mm):");
        String fechaHora = scanner.nextLine();
        System.out.println("Ingrese motivo de la cita:");
        String motivo = scanner.nextLine();

        System.out.println("Doctores disponibles:");
        for (int i = 0; i < listaDoctores.size(); i++) {
            System.out.println((i + 1) + ". " + listaDoctores.get(i));
        }
        System.out.println("Seleccione el ID del doctor:");
        int doctorId = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

        System.out.println("Pacientes disponibles:");
        for (int i = 0; i < listaPacientes.size(); i++) {
            System.out.println((i + 1) + ". " + listaPacientes.get(i));
        }
        System.out.println("Seleccione el ID del paciente:");
        int pacienteId = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

        Cita cita = new Cita(id, fechaHora, motivo, listaDoctores.get(doctorId - 1), listaPacientes.get(pacienteId - 1));
        listaCitas.add(cita);
        System.out.println("Cita creada exitosamente");
    }

    public static void listarCitas() {
        System.out.println("Listado de citas:");
        for (Cita cita : listaCitas) {
            System.out.println(cita);
        }
    }
}
