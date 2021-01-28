package chapter4;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class ImageImporter implements Importer {

	public static final String PATH = "path";
	public static final String WIDTH = "width";
	public static final String HEIGHT = "height";
	public static final String TYPE = "type";
	
//	@Override
//	public Document importFile(File file) throws IOException {
//		
//		final Map<String, String> attributes = new HashMap<>();
//		
//		attributes.put(PATH, file.getPath());
//		
//		final BufferedImage image = ImageIO.read(file);
//		attributes.put(WIDTH, String.valueOf(image.getWidth()));
//		attributes.put(HEIGHT, String.valueOf(image.getHeight()));
//		attributes.put(TYPE, "IMAGE");
//		
//		return new Document(attributes);
//	}
	
	@Override
	public Document importFile(final File file) throws IOException {
		final TextFile textFile = new TextFile(file);
		
		textFile.addLineSuffix(NAME_PREFIX, PATIENT);
		
		final int lineNumber = textFile.addLineSuffix(2,  String :: isEmpty, ADDRES);
		textFile.addLines(lineNumber + 1, (line) -> line.startsWith("regards,"), BODY);
		
		final Map<String, String> attributes = textFile.getAttributes();
		attributes.put(TYPE, "LETTER");
		
		return new Document(attributes);
	}
}
