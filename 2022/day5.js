import { readFile } from './util/util.js';
var input = readFile('/Users/geig006/Development/projects/priv/adventOfCode/2022/input/day5_1');

var startTime = performance.now()


var input = input.split(/\r?\n/);
var sum =0;

input.forEach(line =>  {
  const lineValues = line.split(',');
  var sectionOne = lineValues[0].split('-');
  var sectionTwo = lineValues[1].split('-');
  var arrayOne = [];
  var arrayTwo = [];

  for (let i = parseInt(sectionOne[0]); i <= parseInt(sectionOne[1]); i ++) {
    arrayOne.push(parseInt(i));
  }
  for (let i = parseInt(sectionTwo[0]); i <= parseInt(sectionTwo[1]); i ++) {
    arrayTwo.push(parseInt(i));
  }
  let allFounded = arrayTwo.some(r=> arrayOne.includes(r))
  let allFoundedTwo = arrayOne.some(r=> arrayTwo.includes(r))

  if(allFounded || allFoundedTwo){
    sum++;
  }
});

console.log('sum',sum);
var endTime = performance.now()
console.log(`Call to doSomething took ${endTime - startTime} milliseconds`)