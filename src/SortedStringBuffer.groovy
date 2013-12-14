class SortedStringBuffer {

   private OutputFile bufferOutput

    private int bufferSize
    private def minHeap

    public SortedStringBuffer(OutputFile bufferOutput, int bufferSize, Comparator<String> comparator) {
        this.bufferOutput = bufferOutput
        this.bufferSize = bufferSize
        this.minHeap = new PriorityQueue<String>(10, comparator)
    }

    def add(String line) {
        minHeap.add(line)
        if (minHeap.size() > bufferSize) {
            bufferOutput.appendLine(minHeap.poll())
        }
    }

    def empty() {
        while (!minHeap.isEmpty()) {
            bufferOutput.appendLine(minHeap.poll())
        }
    }
}
