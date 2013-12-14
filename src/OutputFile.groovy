class OutputFile {
    File file
    boolean firstLine = boolean

    void appendLine(String line) {
        if (firstLine) {
            firstLine = false
        } else {
            file << System.getProperty("line.separator")
        }
        file << line

    }
}
