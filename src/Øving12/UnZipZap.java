package Øving12;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UnZipZap {

    private byte[] bytesFromFile;
    private byte[] bytesToFile;
    private int outputLength;

    public void decompress(String filename) {

        // Reads file and populates input array and size of output array
        readFile(filename);

        // Iterate through byte array
        for (int i = 0; i < bytesFromFile.length; i++) {

            byte currentByte = bytesFromFile[i];

            // Compressed data
            if (currentByte > 0) {
                byte offset = bytesFromFile[++i];
                int startIndex = outputLength - offset;

                for (int j = startIndex; j < startIndex + currentByte; j++) {
                    bytesToFile[outputLength] = bytesToFile[j];
                    outputLength++;
                }

                // Uncompressed data
            } else if (currentByte < 0) {
                int length = -currentByte;
                for (int j = i + 1; j <= i + length; j++) {
                    bytesToFile[outputLength] = bytesToFile[j];
                    outputLength++;
                }
                i += length;
            }
        }
        writeFile();
        System.out.println("Decompressed!");
    }

    private void readFile(String filename) {
        String path = "src/Øving12/compressed/" + filename;
        try {
            bytesFromFile = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bytesToFile = new byte[bytesFromFile.length * 2];
    }

    // Writes from bytesToFile array to
    private void writeFile() {
        try (DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("src/Øving12/uncompressed/testfile")))) {
            dos.write(bytesToFile, 0, outputLength);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}