package dtki7.aztec;

public class Main {
    public static void main(String[] args) {
        if (args.length > 1) {
            if (args[0].compareTo("scan") == 0) {
                String outfile = args.length > 2 ? args[2] : "out.bin";
                ToBytes.exec(args[1], outfile);
                return;
            } else if (args[0].compareTo("generate") == 0) {
                String outfile = args.length > 2 ? args[2] : "out.png";
                ToImage.exec(args[1], outfile);
                return;
            }
        }
        System.err.println("Parameters: scan|generate <input file> [<output file>]");
    }
}
