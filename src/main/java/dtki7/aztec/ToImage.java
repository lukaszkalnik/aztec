package dtki7.aztec;

import boofcv.alg.fiducial.aztec.AztecCode;
import boofcv.alg.fiducial.aztec.AztecEncoder;
import boofcv.alg.fiducial.aztec.AztecGenerator;
import boofcv.io.image.ConvertBufferedImage;
import boofcv.io.image.UtilImageIO;
import boofcv.struct.image.GrayU8;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class ToImage {
    public static void exec(String infile, String outfile) {
        byte[] message;
        try {
            message = FileUtils.readFileToByteArray(new File(infile));
        } catch (IOException e) {
            System.err.println("Error opening file " + infile);
            return;
        }

        AztecCode marker = new AztecEncoder().addBytes(message, 0, message.length).fixate();
        
        GrayU8 rendered = AztecGenerator.renderImage(10, 1, marker);
        BufferedImage output = ConvertBufferedImage.convertTo(rendered, null);
        UtilImageIO.saveImage(output, outfile);
    }
}
