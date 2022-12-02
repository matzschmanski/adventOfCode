import { readFile } from './util/util.js';

var input = readFile('/Users/geig006/Development/projects/priv/adventOfCode/2022/input/day1_1');

var highest =[];
var currentElv =0;
input.split(/\r?\n/).forEach(line =>  {
    if(line ===''){
        highest.push(currentElv);
        currentElv=0;
    }else{
        currentElv+=parseInt(line)
    }
  });
  console.log(highest.sort(function(a, b) {
    return a - b;
  }).slice(highest.length-3).reduce((partialSum, a) => partialSum + a, 0));


