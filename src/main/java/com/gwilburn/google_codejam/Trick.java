package com.gwilburn.google_codejam;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by Greg on 5/31/14.
 * This class is to handle the workings of the magician's card trick
 * from Google Codejam 2014 Qualification round A
 * https://code.google.com/codejam/contest/2974486/dashboard
 */
class Trick {
    private int[][] set1;
    private int[][] set2;
    private int choice1;
    private int choice2;
    private final BufferedReader input;

    /**
     * Set up a new Trick
     *
     * @param input - BufferedReader to read data from
     * @throws IOException - Allow the user of this class to handle Exceptions as they see fit
     */
    Trick(BufferedReader input) throws IOException {
        set1 = new int[4][4];
        set2 = new int[4][4];
        this.input = input;

        choice1 = readChoice();
        set1 = readSet();

        choice2 = readChoice();
        set2 = readSet();
    }

    /**
     * Read the matrix of cards from the BufferedReader.
     *
     * @return - Returns the matrix of card distribution from the input file
     * @throws IOException
     */
    int[][] readSet() throws IOException {
        int[][] set = new int[4][4];
        for (int row = 0; row < 4; row++) {
            String[] data = input.readLine().split(" ");
            for (int col = 0; col < data.length; col++) {
                set[row][col] = Integer.parseInt(data[col], 10);
            }
        }
        return set;
    }

    /**
     * Read the audience volunteer's choice from the BufferedReader
     *
     * @return - Returns the row number the audience volunteer chose from the file.
     * @throws IOException
     */
    int readChoice() throws IOException {
        String line = input.readLine();
        return Integer.parseInt(line, 10) - 1;
    }

    /**
     * Determine the outcome of the trick, was it a bad magician, a cheating audience member,
     * or was there a single card common to both choices?
     *
     * @return - String containing the result of the trick.
     */
    String getResult() {
        int[] row1 = set1[choice1];
        int[] row2 = set2[choice2];
        int lastMatch = 0;
        int numberPossibilities = 0;
        String result;

        // Cards may have changed location in the row instead of moving to a different row!
        for (int iterOuter = 0; iterOuter < row1.length; iterOuter++) {
            for (int iterInner = 0; iterInner < row2.length; iterInner++) {
                if (row1[iterOuter] == row2[iterInner]) {
                    numberPossibilities++;
                    lastMatch = row1[iterOuter];
                }
            }
        }

        if (numberPossibilities < 1) {
            result = "Volunteer cheated!";
        } else if (numberPossibilities > 1) {
            result = "Bad magician!";
        } else {
            result = Integer.toString(lastMatch, 10);
        }

        return result;
    }
}
