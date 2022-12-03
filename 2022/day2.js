import { readFile } from './util/util.js';

var input = readFile('/Users/geig006/Development/projects/priv/adventOfCode/2022/input/day2_1');

var highest =[];
input.split(/\r?\n/).forEach(line =>  {
    const lineValues = line.split(' ');
    highest.push(calculateMatchPoints(lineValues[0],lineValues[1]))
  });
  console.log(highest.reduce((partialSum, a) => partialSum + a, 0));

  function calculateMatchPoints(inputElv, inputPlayer){
    const ROCK_ELV = "A";
    const SCISSORS_ELV = "C";
    const PAPER_ELV = "B";
    const DRAW = "Y";
    const LOOSE = "X";
    if(ROCK_ELV === inputElv){
        if(LOOSE === inputPlayer){
            return 3;
        }else if (DRAW === inputPlayer){
            return 4;
        }else {
            return 8;
        }
    }else if(PAPER_ELV === inputElv){
        if(LOOSE === inputPlayer){
            return 1;
        }else if (DRAW === inputPlayer){
            return 5;
        }else {
            return 9;
        }

    }else if (SCISSORS_ELV === inputElv){
        if(LOOSE === inputPlayer){
            return 2;
        }else if (DRAW === inputPlayer){
            return 6;
        }else {
            return 7;
        }
    }
  }
