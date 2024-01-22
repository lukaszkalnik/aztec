# aztec

A simple command line tool using [BoofCV](https://boofcv.org) to encode and decode aztec codes.

## Usage

Build and run

```bash
mvn package
java -jar target/aztec-*.jar

# examples
java -jar target/aztec-*.jar scan aztec.png data.bin
java -jar target/aztec-*.jar data.bin aztec.png
```

Parameters: `scan|generate <input file> [<output file>]`

- `scan`: scan an image and save bytes in file
- `generate`: generate an image from bytes in file
- `input file`: an image file when scanning, any when generating
- `output file`: any file when scanning (default: `out.bin`), an image file when generating (default: `out.png`)
