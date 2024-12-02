import {readTxtFileLines} from '../snippets/read_input';




export const main = (): string => {
    var input = readTxtFileLines("002.txt");
    var safe_count = 0;

    // rule 1 all increase and value is okay
    for (var i = 0; i < input.length; i++) {
        if(check_rules(input[i])) {
            safe_count++;
        }
    }

    // rule 2 all decrease and value is okay

    return safe_count.toString();
}
// function check_rules(level_report: string): boolean {
//     var level_report_split = level_report.split(' ');
//     var state = null;
    
//     for (var i = 0; i < level_report_split.length-1; i++) {
//         var curr = parseInt(level_report_split[i]);
//         var prev = parseInt(level_report_split[i + 1]);
//         var difference = curr - prev;
//         if(state == null) {
//             if(difference > 0) {
//                 state = 'DESC';
//             }else if(difference < 0) {
//                 state = 'ASC';
//             }
//         }
//         if(difference > 2 || difference < -2) {
//             console.log('report: ' + level_report_split + ' is unsafe (diff) ' + state+ ' diff: '+ difference);
//             return false;
//         }
//         if(difference <= 2 && difference >0 && state == 'ASC') {
//             console.log('report: ' + level_report_split + ' is unsafe (state) ASC ' + state + ' diff: '+ difference);
//             return false;
//         }

//         if(difference <= -2 && difference<0 && state == 'DESC') {
//             console.log('report: ' + level_report_split + ' is unsafe (state) DESC ' + state + ' diff: '+ difference);
//             return false;
//         }
//     }
//     console.log('report: ' + level_report_split + ' is safe ' + state);
//     return true;
// }

 
console.log(main());
