package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
// proyecto acorde especificación
public class ConexionBD {
	
	private static String database="biblioteca"; // bbdd correcta
	private static String usuario="root";
	private static String contrasena="";
	private static String url="jdbc:mariadb://localhost/" + database; 
	//Objeto Connection que debemos usar en JDBC
	private Connection conexion= null;
	/**
	 * Método de la clase que devuelve el objeto Connection necesario para operar con la base de datos.
	 * @return el objeto Connection
	 */
	public Connection getConexion() {
		if(this.conexion!= null)
		{// Ya está la conexió creada, la devuelvo
			return this.conexion;
			
			}
		//Inicializamos la conexión a la base de datos
		//Registrar el driver. Previamente habrá que haber añadido el driver al proyecto (Build Path)
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			//Obtenemos el objeto Connection de la clase
			//DriverManager. Lanzará una excepción
			//SQLException si no se puede conectar
			this.conexion =DriverManager.getConnection(url, usuario, contrasena);
			System.out.println("Conexion a base de datos correcta");
			
		} catch (ClassNotFoundException e) {
			System.out.println("error al registrarel driver");
		} catch (SQLException e) {
		System.out.println("No se puede conectar con la base de datos."+e.getLocalizedMessage());
		}
		return conexion;
		}
	/**
	 * Método de la clase que libera los recursos asociados a la conexión 
	 */
	public void desconectar() {
		if (this.conexion!=null) {
			try {
				this.conexion.close();
				
			} catch(SQLException e) {
				System.out.println("Error, no se puede liberar la conexión");
			}
		}
		
	}
}
