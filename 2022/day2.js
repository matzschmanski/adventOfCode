import { readFile } from './util/util.js';

var input = readFile('/Users/geig006/Development/projects/priv/adventOfCode/2022/input/day2_1');

var highest =[];
var currentElv =0;
input.split(/\r?\n/).forEach(line =>  {
    const lineValues = line.split(' ');
    highest.push(calculateMatchPoints(lineValues[0],lineValues[1]))
  });
  console.log(highest.reduce((partialSum, a) => partialSum + a, 0));


  /**
   * A for Rock, B for Paper, and C for Scissors.
   * X for Rock, Y for Paper, and Z for Scissors.
   * 1 for Rock, 2 for Paper, and 3 for Scissors) plus the score for the outcome of the round 
   * (0 if you lost, 3 if the round was a draw, and 6 if you won)
   */

  function calculateMatchPoints(inputElv, inputPlayer){
    const ROCK_ELV = "A";
const PAPER_ELV = "B";
const SCISSORS_ELV = "C";
const ROCK_PLAYER = "X";
const PAPER_PLAYER = "Y";
    if(ROCK_ELV === inputElv){
        if(ROCK_PLAYER === inputPlayer){
            return 4;
        }else if (PAPER_PLAYER === inputPlayer){
            return 8;
        }else {
            return 3;
        }
    }else if(PAPER_ELV === inputElv){
        if(ROCK_PLAYER === inputPlayer){
            return 1;
        }else if (PAPER_PLAYER === inputPlayer){
            return 5;
        }else {
            return 9;
        }

    }else if (SCISSORS_ELV === inputElv){
        if(ROCK_PLAYER === inputPlayer){
            return 7;
        }else if (PAPER_PLAYER === inputPlayer){
            return 2;
        }else {
            return 6;
        }
    }
  }
