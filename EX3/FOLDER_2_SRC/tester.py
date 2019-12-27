from pathlib import Path
from subprocess import *
import os

PRINT_TO_CONSOLE = True
TEST_RESULT = "./FOLDER_5_OUTPUT/test_results.txt"  # if PRINT_TO_CONSOLE is false
SKIP = ["Input"]


def main():
    total = 0
    passed = 0

    input_dir = Path("./FOLDER_4_INPUT")
    expected_output_dir = Path("./FOLDER_6_EXPECTED_OUTPUT")
    output_file = Path("./FOLDER_5_OUTPUT/test_out.txt")
    if not PRINT_TO_CONSOLE:
        test_result_file = open("./FOLDER_5_OUTPUT/test_results.txt", "w")

    for test in input_dir.iterdir():
        test_name = test.stem

        if test_name in SKIP:
            continue

        total += 1

        test_expected_output = expected_output_dir / (test_name + "_Expected_Output.txt")

        os.system(f'java -jar ./COMPILER {test} {output_file}')  # execute test

        actual = open(output_file).read()
        try:
            expected = open(test_expected_output).read()
        except FileNotFoundError:
            res = f'{test_name} - no expected output provided'
            print(res) if PRINT_TO_CONSOLE else test_result_file.write(res)
            continue

        if expected == actual:
            res = f'{test_name} - passed with result: {expected[:-1]}'
            print(res) if PRINT_TO_CONSOLE else test_result_file.write(res)
            passed += 1
        else:
            res = f'{test_name} - failed: expected {expected[:-1]} but found {actual[:-1]}'
            print(res) if PRINT_TO_CONSOLE else test_result_file.write(res)

    res = f"\nSUMMARY: {passed}/{total} tests passed"
    print(res) if PRINT_TO_CONSOLE else test_result_file.write(res)
    
    if not PRINT_TO_CONSOLE:
        test_result_file.close()



if __name__ == '__main__':
    main()
