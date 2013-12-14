class MergeAndSortTest extends GroovyTestCase {

    void testTwoEmptyFiles_ProduceEmptyFile() {
        def mergedAndSortedFile = new MergeAndSort().mergeAndSort(new File('empty_file.txt'), new File('empty_file.txt'))
        assert mergedAndSortedFile.text.isEmpty()
    }

    void testAlreadySortedFile_EmptyFile_ProduceSortedFile() {
        def sortedFile = new File('sorted_file.txt')

        def mergedAndSortedFile = new MergeAndSort().mergeAndSort(sortedFile, new File('empty_file.txt'))
        assert mergedAndSortedFile.text == sortedFile.text

        def mergedAndSortedFile2 = new MergeAndSort().mergeAndSort(new File('empty_file.txt'), sortedFile)
        assert mergedAndSortedFile2.text == sortedFile.text
    }

    void testAlreadySortedFile_AlreadySortedFile_ProduceSortedFile() {
        def mergedAndSortedFile = new MergeAndSort().mergeAndSort(new File('sorted_file.txt'), new File('sorted_file.txt'))
        assert mergedAndSortedFile.text == new File('two_sorted_files.txt').text
    }

    void testTwoSortedFileS_ProduceSortedFile() {
        def mergedAndSortedFile = new MergeAndSort().mergeAndSort(new File('sorted_file.txt'), new File('another_sorted_file.txt'))
        assert mergedAndSortedFile.text == new File('sorted_file_and_another_sorted_file.txt').text

        def mergedAndSortedFile2 = new MergeAndSort().mergeAndSort(new File('another_sorted_file.txt'), new File('sorted_file.txt'))
        assert mergedAndSortedFile2.text == new File('sorted_file_and_another_sorted_file.txt').text
    }


    void testAlmostSortedFile_EmptyFile_ProduceSortedFile() {
        def mergedAndSortedFile = new MergeAndSort().mergeAndSort(new File('almost_sorted_file.txt'), new File('empty_file.txt'), 1)
        assert mergedAndSortedFile.text == new File('sorted_file.txt').text

        def mergedAndSortedFile2 = new MergeAndSort().mergeAndSort(new File('empty_file.txt'), new File('almost_sorted_file.txt'), 1)
        assert mergedAndSortedFile2.text == new File('sorted_file.txt').text

        def mergedAndSortedFile3 = new MergeAndSort().mergeAndSort(new File('empty_file.txt'), new File('almost_sorted_file2.txt'), 1)
        assert mergedAndSortedFile3.text == new File('sorted_file.txt').text
    }

    void testReverseSortedFile_EmptyFile_ProduceSortedFile() {
        def mergedAndSortedFile = new MergeAndSort().mergeAndSort(new File('reverse_sorted_file.txt'), new File('empty_file.txt'), 2)
        assert mergedAndSortedFile.text == new File('sorted_file.txt').text
    }


    void testGivenExample() {
        def mergedAndSortedFile = new MergeAndSort().mergeAndSort(new File('given_example1.txt'), new File('given_example2.txt'), 3)
        assert mergedAndSortedFile.text == new File('given_example_result.txt').text
    }

    void testWeirdCase() {
        def mergedAndSortedFile = new MergeAndSort().mergeAndSort(new File('weird_case1.txt'), new File('weird_case2.txt'), 1)
        assert mergedAndSortedFile.text == new File('weird_case_result.txt').text
    }
}
