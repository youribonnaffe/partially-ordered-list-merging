import OutputFile

class MergeAndSort {

    static final Comparator<String> DEFAULT_COMPARATOR = [compare: { s1, s2 -> s1.compareTo(s2) }] as Comparator

    static File mergeAndSort(File file1, File file2, int maxSortOffset = 0) {
        File sortedFile1 = sort(file1, maxSortOffset)
        File sortedFile2 = sort(file2, maxSortOffset)

        return mergeLineByLine(sortedFile1, sortedFile2)
    }

    static File sort(File file, int maxSortOffset) {
        def sortedFile = new OutputFile(file: File.createTempFile("sortedFile", null))
        def buffer = new SortedStringBuffer(sortedFile, maxSortOffset, DEFAULT_COMPARATOR)

        file.eachLine { line ->
            buffer.add(line)
        }
        buffer.empty()

        return sortedFile.file
    }

    static File mergeLineByLine(File file1, File file2) {
        def reader1 = file1.newReader()
        def reader2 = file2.newReader()

        String line1 = reader1.readLine()
        String line2 = reader2.readLine()

        OutputFile mergedFile = new OutputFile(file: File.createTempFile("mergedFile", null))

        while (line1 && line2) {
            if (DEFAULT_COMPARATOR.compare(line1, line2) < 0) {
                mergedFile.appendLine(line1)
                line1 = reader1.readLine()
            } else {
                mergedFile.appendLine(line2)
                line2 = reader2.readLine()
            }
        }

        copyFileStartingAtLine(reader1, line1, mergedFile)
        copyFileStartingAtLine(reader2, line2, mergedFile)

        return mergedFile.file
    }

    private static void copyFileStartingAtLine(BufferedReader sourceFile, String startingLine, OutputFile outputFile) {
        while (startingLine) {
            outputFile.appendLine(startingLine)
            startingLine = sourceFile.readLine()
        }
    }


}
