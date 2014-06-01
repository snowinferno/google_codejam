package com.gwilburn.google_codejam;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by Greg on 5/30/14.
 * Entry point for Codejam Qualification challenge A 2014.
 */
class QualificationA {
    private final static Logger logger = LogManager.getLogger(Trick.class.getName());
    static final int MISSING_ARGUMENT = 9;
    static final int FILE_READ_ERROR = 2;

    public static void main(String[] args) {
        // Have to receive input of the filename.
        if (args.length < 1) {
            logger.error("You must supply a file with the input.");
            System.exit(MISSING_ARGUMENT);
        }

        String filePath = args[0];
        // grab the path to the file so we can open a BufferedReader
        Path p = FileSystems.getDefault().getPath(filePath);

        try {
            BufferedReader in = Files.newBufferedReader(p, StandardCharsets.UTF_8);
            int numTrials = Integer.parseInt(in.readLine(), 10);
            for (int trial = 0; trial < numTrials; trial++) {
                Trick trick = new Trick(in);
                System.out.println("Case #" + (trial + 1) + ": " + trick.getResult());
            }
            in.close();
        } catch (IOException e) {
            logger.error(e);
            System.exit(FILE_READ_ERROR);
        }
    }

}
