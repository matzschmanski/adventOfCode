import { readTxtFileLines } from '../snippets/read_input';




export const main = (): string => {
    var input = readTxtFileLines("002.txt");
    var safe_count = 0;

    // rule 1 all increase and value is okay
    for (var i = 0; i < input.length; i++) {
        if (isSafe(input[i])) {
            console.log(input[i] + ' is safe');
            safe_count++;
        }else{
            console.log(input[i] + ' is not safe');
        }
    }

    // rule 2 all decrease and value is okay

    return safe_count.toString();
}

function isSafe(level_report: string): boolean {
    if (isAlwayIncreasing(level_report) || isAlwaysDecreasing(level_report)) {
        return true;
    }
    return false;
}

function isAlwayIncreasing(level_report: string): boolean {
    var level_report_split = level_report.split(' ').map(Number);
    for (var i = 1; i < level_report_split.length; i++) {
        if(level_report_split[i] == level_report_split[i - 1]){
            return false;
        }
        if (level_report_split[i] <= level_report_split[i - 1] || (level_report_split[i] - level_report_split[i - 1]) > 3) {
            return false;
        }
    }
    return true;
}
function isAlwaysDecreasing(level_report: string): boolean {
    var level_report_split = level_report.split(' ').map(Number);
    for (var i = 1; i < level_report_split.length; i++) {
        if(level_report_split[i] == level_report_split[i - 1]){
            return false;
        }
        if (level_report_split[i] >= level_report_split[i - 1] || (level_report_split[i - 1] - level_report_split[i]) > 3) {
            return false;
        }
    }
    return true;
}


console.log(main());
