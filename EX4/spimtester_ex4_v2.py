from pathlib import Path
from subprocess import *
import os

PRINT_TO_CONSOLE = True
TEST_RESULTS = "./FOLDER_5_OUTPUT/test_results.txt"  # if PRINT_TO_CONSOLE is false
SKIP = ["Input"]
OUTPUT_FILENAME = "SemanticStatus"
REFERENCE_JAR = "COMPILER_REF"
SHOW_DIFF = True

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
        test_expected_output = expected_output_dir / (test_name + "_EXPECTED_OUTPUT.txt")

        if test_name in SKIP:
            continue

        total += 1

        os.system(f'java -jar ./COMPILER {test} {output_file}')  # get MIPS instruction
        os.system(f'spim -f {output_file} > {spim_output}')  # launch them

        actual = open(output_file).read()
        try:
            expected = open(test_expected_output).read()
        except FileNotFoundError:
            res = f'{test_name} - no expected output provided'
            print(res) if PRINT_TO_CONSOLE else test_result_file.write(res)
            continue

        if expected == actual:
            res = f'{test_name} - succeeded '  # with result: {expected[:-1]}'
            print(res) if PRINT_TO_CONSOLE else test_result_file.write(res)
            passed += 1
        else:
            res = f'{test_name} - failed'  # : expected {expected[:-1]} but found {actual[:-1]}'
            print(res) if PRINT_TO_CONSOLE else test_result_file.write(res)
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
