import { readTxtFile } from '../snippets/read_input';




export const main = (): string => {
    var input = readTxtFile("003.txt");
    // regex for the input mul\(^\)*)
    // var input = readTxtFileLines("003.txt");

    var matches;
    var sum = 0;
    //var regexp = new RegExp('mul\((^\)*)');
    var regexp = new RegExp('mul\\((\\d+),(\\d+)\\)', 'g');
    console.log('regex', regexp)
    do {
        matches = regexp.exec(input);
        if (matches) {
            
            console.log(matches[1]);
            console.log(matches[2]);
            sum += parseInt(matches[1]) * parseInt(matches[2]);
        }
    } while (matches);

    return sum.toString();
}

console.log(main());
