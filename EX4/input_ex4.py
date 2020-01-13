from pathlib import Path
from subprocess import *
import os

SKIP = ["Input"]
OUTPUT_FILENAME = "SemanticStatus"
REFERENCE_JAR = "COMPILER_REF"


def main(argv):
    input_dir = Path("./FOLDER_4_INPUT")

    if len(argv) == 1:
        input = 'Input.txt'
    else:
        input = None
        target = argv[1].lower()
        for test in input_dir.iterdir():
            test_name = test.stem.lower()
            if test_name.startswith(target):
                input = test.stem + ".txt"
                break

    if not input:
        print('Error: test not found')
        exit(0)

    output_file = Path("./FOLDER_5_OUTPUT") / (OUTPUT_FILENAME + ".txt")
    output_file_ref = Path("./FOLDER_5_OUTPUT") / (OUTPUT_FILENAME + "_REF" + ".txt")

    os.system(f'java -jar ./COMPILER {input_dir / input} {output_file}')  # execute test on our program
    os.system(f'java -jar ./{REFERENCE_JAR} {input_dir / input} {output_file_ref}')  # execute test on our program

    actual = open(output_file).read()
    expected = open(output_file_ref).read()
    print(f'Test name: {input}')
    if expected == actual:
        print(f'Result: passed')
    else:
        print(f'Result: failed')
        print('Difference:')
        os.system(f'diff {output_file} {output_file_ref}')  # execute test on our program


if __name__ == '__main__':
    import sys
    main(sys.argv)
