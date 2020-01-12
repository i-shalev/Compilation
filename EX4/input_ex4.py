from pathlib import Path
from subprocess import *
import os

PRINT_TO_CONSOLE = True
TEST_RESULTS = "./FOLDER_5_OUTPUT/test_results.txt"  # if PRINT_TO_CONSOLE is false
SKIP = ["Input"]
OUTPUT_FILENAME = "SemanticStatus"
REFERENCE_JAR = "COMPILER_REF"


def main():
    input = Path("./FOLDER_4_INPUT/Input.txt")
    output_file = Path("./FOLDER_5_OUTPUT") / (OUTPUT_FILENAME + ".txt")
    output_file_ref = Path("./FOLDER_5_OUTPUT") / (OUTPUT_FILENAME + "_REF" + ".txt")

    if not PRINT_TO_CONSOLE:
        test_result_file = open(TEST_RESULTS, "w")

    os.system(f'java -jar ./COMPILER {input} {output_file}')  # execute test on our program
    os.system(f'java -jar ./{REFERENCE_JAR} {input} {output_file_ref}')  # execute test on our program

    actual = open(output_file).read()
    expected = open(output_file_ref).read()

    if expected == actual:
        res = f'Test passed'
        print(res) if PRINT_TO_CONSOLE else test_result_file.write(res)
    else:
        res = f'Test failed'
        os.system(f'diff {output_file} {output_file_ref}')  # execute test on our program
        print(res) if PRINT_TO_CONSOLE else test_result_file.write(res)

    if not PRINT_TO_CONSOLE:
        test_result_file.close()


if __name__ == '__main__':
    main()
