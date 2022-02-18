import java.io.File;
import java.util.*;

public class Dades {
	public static String iDes;    // Imatge Desenvolupament
	public static String iPrd;    // Imatge Producció
	public static String fSap;    // Fitxer sap.jcoDestination
	public static String rutaRF;  // Ruta RF
	public static String rutaCP;  // Ruta CP
	public static String fSapDES; // Fitxer sap_DES.jcoDestination
	public static String fSapPRD; // Fitxer sap_PRD.jcoDestination
	
	private Hashtable<String, String > propietats;
	
	public Dades() {
		this.propietats = getProperties();
		iPrd = "src/" + propietats.get("iPrd");
		iDes = "src/" + propietats.get("iDes");
		fSap = propietats.get("fSap");
		rutaRF = propietats.get("rutaRF");
		rutaCP = propietats.get("rutaCP");
		fSapDES = propietats.get("fSapDES");
		fSapPRD = propietats.get("fSapPRD");		
	}
	
	/**
	 * Carregar dades arxiu propietats
	 */
	private Hashtable<String, String> getProperties() {
		Hashtable<String,String> relacions = new Hashtable<String, String>();
		
		java.util.Properties prop = new java.util.Properties();
		String fitx = "src/propietats.properties";
			
		try {
			// Carregar .properties al hashtable
			java.io.FileInputStream file = new java.io.FileInputStream(fitx);
			prop.load(file);
			for (Map.Entry<Object, Object> entry : prop.entrySet()) {
		        relacions.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
		    }
			file.close();
					
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return relacions;
	}

}
