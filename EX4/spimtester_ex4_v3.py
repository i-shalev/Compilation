from pathlib import Path
from subprocess import *
import os

PRINT_TO_CONSOLE = True
TEST_RESULTS = "./FOLDER_5_OUTPUT/test_results.txt"  # if PRINT_TO_CONSOLE is false
SKIP = ["Input"]
OUTPUT_FILENAME = "SemanticStatus"
REFERENCE_JAR = "COMPILER_REF"
SHOW_DIFF = False

def compareFiles(f1, f2):
  for line in difflib.unified_diff(f1, f2, fromfile='file1', tofile='file2', lineterm=''):
    return False
  return True

def main():
    total = 0
    passed = 0

    input_dir = Path("./FOLDER_4_INPUT")
    expected_output_dir = Path("./FOLDER_6_EXPECTED_OUTPUT")
    output_file = Path("./FOLDER_5_OUTPUT") / (OUTPUT_FILENAME + ".txt")

    spim_output = Path("./FOLDER_5_OUTPUT") / "SPIM_Result.txt"

    if not PRINT_TO_CONSOLE:
        test_result_file = open(TEST_RESULTS, "w")

    for test in input_dir.iterdir():
        test_name = test.stem
        test_expected_output = expected_output_dir / (test_name + ".txt")

        if test_name in SKIP:
            continue

        total += 1

        os.system(f'java -jar ./COMPILER {test} {output_file}')  # get MIPS instruction
        os.system(f'spim -f {output_file} > {spim_output}')  # launch them

        actual = open(spim_output)
        try:
            expected = open(test_expected_output)
        except FileNotFoundError:
            res = f'{test_name} - no expected output provided'
            print(res) if PRINT_TO_CONSOLE else test_result_file.write(res)
            continue

        actual.readline()
        actual.readline()
        actual.readline()
        actual.readline()
        actual.readline()
        line1 = actual.readline().replace('\r', '').replace('\n', '')
        expected.readline()
        expected.readline()
        expected.readline()
        expected.readline()
        expected.readline()
        line2 = expected.readline().replace('\r', '').replace('\n', '')
        if line1 == line2:
            res = f'{test_name} - succeeded '  # with result: {expected[:-1]}'
            print(res) if PRINT_TO_CONSOLE else test_result_file.write(res)
            passed += 1
        else:
            res = f'{test_name} - failed'  # : expected {expected[:-1]} but found {actual[:-1]}'
            print(res) if PRINT_TO_CONSOLE else test_result_file.write(res)
            print()
            print(line1)
            print(line2)
            print()
            if SHOW_DIFF:
                print('\ndifference:\n')
                os.system(f'diff {spim_output} {test_expected_output}')
                print('\n')

    res = f"\nSUMMARY: {passed}/{total} tests passed"
    print(res) if PRINT_TO_CONSOLE else test_result_file.write(res)

    if not PRINT_TO_CONSOLE:
        test_result_file.close()


if __name__ == '__main__':
    main()