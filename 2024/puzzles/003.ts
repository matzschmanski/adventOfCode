import { readTxtFile, readTxtFileLines } from '../snippets/read_input';




export const main = (): string => {
    var input = readTxtFile("003.txt");
    //replace all new lines
    input = input.replace(/\n/g, ',');
    var sum = 0;
    input = input.replace(/don't\(\)[\s\S]*?do\(\)/g, '');
    input = input.replace(/don't\(.*/, '');
        console.log(input);
        // regex for the input mul\(^\)*)
        // var input = readTxtFileLines("003.txt");

        var matches;

        //var regexp = new RegExp('mul\((^\)*)');
        var regexp = new RegExp('mul\\((\\d+),(\\d+)\\)', 'g');
        do {
            matches = regexp.exec(input);
            if (matches) {
                sum += parseInt(matches[1]) * parseInt(matches[2]);
            }
        } while (matches);

    return sum.toString();
}

console.log(main());
