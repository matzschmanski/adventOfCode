import { readFile } from '../2022/util/util.js';

var input = readFile('/Users/geig006/Development/projects/priv/adventOfCode/2015/input/day2_1');

var totalPaper = 0;
var totalRibbon = 0;

input.split(/\r?\n/).forEach(line => {
  var lineSplit = line.split('x');

  for (let index = 0; index < lineSplit.length; index++) {
    lineSplit[index] = parseInt(lineSplit[index]);
  }
  lineSplit = lineSplit.sort(function (a, b) { return a - b; });
  //var valueText ='(2*'+lineSplit[0]+'*'+lineSplit[1]+')+(2*'+lineSplit[1]+'*'+lineSplit[2]+')+(2*'+lineSplit[0]+'*'+lineSplit[2]+')+('+lineSplit[0]+'*'+lineSplit[1]+')';
  var value = (2 * lineSplit[0] * lineSplit[1]) + (2 * lineSplit[1] * lineSplit[2]) + (2 * lineSplit[0] * lineSplit[2]) + (lineSplit[0] * lineSplit[1]);
  totalPaper += value;
});

input.split(/\r?\n/).forEach(line => {
  var lineSplit = line.split('x');

  for (let index = 0; index < lineSplit.length; index++) {
    lineSplit[index] = parseInt(lineSplit[index]);
  }
  lineSplit = lineSplit.sort(function (a, b) { return a - b; });
  console.log('lineSplit', lineSplit);
  // var valueText = '(' + lineSplit[0] + '+' + lineSplit[0] + '+' + lineSplit[1] + '+' + lineSplit[1] + ')+(' + lineSplit[0] + '*' + lineSplit[1] + '*' + lineSplit[2] + ')';
  var value = (lineSplit[0] + lineSplit[0] + lineSplit[1] + lineSplit[1]) + (lineSplit[0] * lineSplit[1] * lineSplit[2]);
  // console.log('valueText', valueText);
  console.log(value);
  totalRibbon += value;
  // console.log('', totalRibbon);
});

console.log('total paper:', totalPaper);
console.log('total ribbon:', totalRibbon);



