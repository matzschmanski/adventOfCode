import { readFile } from '../util/util.js';
var rawInput = readFile('/Users/geig006/Development/projects/priv/adventOfCode/2022/input/day6_1');
var startTime = performance.now()
var index =0;
while(index<rawInput.length){
var strong = rawInput.substring(index,index+14);
console.log('repeats', strong, hasRepeats(strong),index+14);
if(!hasRepeats(strong)){
  break;
}
index++;
}
var endTime = performance.now()
console.log(`Call to doSomething took ${endTime - startTime} milliseconds`);

function hasRepeats (str) {
  return /(.).*\1/.test(str);
}