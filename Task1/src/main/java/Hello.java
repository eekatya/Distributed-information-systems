import org.apache.commons.cli.*;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;

public class Hello {
    private static Logger LOGGER = LoggerFactory.getLogger(Hello.class);
    private static File input;
    private static File output;
    private static void unpackedArchive() throws IOException {
        byte[] buffer = new byte[16384];
        BZip2CompressorInputStream ar = new BZip2CompressorInputStream( new FileInputStream(input));
        FileOutputStream out = new FileOutputStream(output);
        int len;
        while ((len = ar.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
        ar.close();
        out.close();
    }
    public static void main(String[] args) throws IOException{
        LOGGER.info("Application started");
        String inputPathname = "RU-NVS.osm.bz2";
        String outputPathname = "RU-NVS.osm";
        Options options = new Options();
        options.addOption("in",  false, "Hello world option");
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse( options, args);
        } catch (ParseException pe) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp( "Hello", options );
        }
        if (cmd.hasOption("in")) {
            System.out.println("Hello world!");
        }
        input = new File(inputPathname);
        output = new File(outputPathname);
        if (!input.exists())
        {
            LOGGER.error("Create input archive, please");
            return;
        }
        if (!output.exists())
        {
            LOGGER.info("Start unpacking");
            unpackedArchive();
            LOGGER.info("Stop unpacking");
        }
        if (!output.exists())
        {
            LOGGER.error("Сould not unpack");
            return;
        }
        XMlParser XMLparser = new XMlParser();
        XMLparser.parseXML(outputPathname);
    }
}
