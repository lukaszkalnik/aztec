package dtki7.aztec;

import boofcv.abst.fiducial.AztecCodePreciseDetector;
import boofcv.alg.fiducial.aztec.AztecCode;
import boofcv.factory.fiducial.ConfigAztecCode;
import boofcv.factory.fiducial.FactoryFiducial;
import boofcv.io.image.ConvertBufferedImage;
import boofcv.io.image.UtilImageIO;
import boofcv.struct.image.GrayU8;
import org.apache.commons.io.FileUtils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ToBytes {
    public static void exec(String infile, String outfile) {
        BufferedImage input = UtilImageIO.loadImageNotNull(infile);
        GrayU8 gray = ConvertBufferedImage.convertFrom(input, (GrayU8) null);

        ConfigAztecCode config = new ConfigAztecCode();
        AztecCodePreciseDetector<GrayU8> detector = FactoryFiducial.aztec(config, GrayU8.class);

        detector.process(gray);

        List<AztecCode> detections = detector.getDetections();
        List<AztecCode> failures = detector.getFailures();

        if (failures.size() > 0) {
            System.err.println("Found " + failures.size() + " undecodable codes!");
            return;
        }

        if (detections.size() != 1) {
            System.err.println("Found " + detections.size() + " codes!");
            return;
        }

        if (outfile != null) {
            try {
                FileUtils.writeStringToFile(new File(outfile), detections.get(0).message, StandardCharsets.ISO_8859_1);
            } catch (IOException e) {
                System.err.println("Error opening file " + outfile);
                return;
            }
        } else {
            System.out.println(detections.get(0).message);
        }
    }
}
