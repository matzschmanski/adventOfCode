import { readFile } from './util/util.js';

var input = readFile('/Users/geig006/Development/projects/priv/adventOfCode/2022/input/day3_1');
var startTime = performance.now()
var charsLower ='abcdefghijklmnopqrstuvwxyz';
var charsUpper ='ABCDEFGHIJKLMNOPQRSTUVWXYZ';
var inBoth =[];

var input = input.split(/\r?\n/);

var sum =0;
var chunkSize=3
for (let i = 0; i < input.length; i += chunkSize) {
    const chunk = input.slice(i, i + chunkSize);
    const letter = chunk[0].split('').filter(value => chunk[1].split('').includes(value)).filter(value => chunk[2].split('').includes(value));
    inBoth.push(letter[0]);
}


// while( input.length>0){
// const letter = input[0].split('').filter(value => input[1].split('').includes(value)).filter(value => input[2].split('').includes(value));
// inBoth.push(letter[0]);
// input= input.slice(3);
// }

  for (var i = 0; i < inBoth.length; i++) { 
  if(inBoth[i] == inBoth[i].toUpperCase()){
    sum +=charsUpper.indexOf(inBoth[i])+27;
  } else if(inBoth[i] == inBoth[i].toLowerCase()){
    sum +=charsLower.indexOf(inBoth[i])+1;
  }
  
}
console.log('sum',sum);
var endTime = performance.now()
console.log(`Call to doSomething took ${endTime - startTime} milliseconds`)