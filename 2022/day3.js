import { readFile } from './util/util.js';

var input = readFile('/Users/geig006/Development/projects/priv/adventOfCode/2022/input/day3_1');

var charsLower ='abcdefghijklmnopqrstuvwxyz';
var charsUpper ='ABCDEFGHIJKLMNOPQRSTUVWXYZ';
var inBoth =[];
input.split(/\r?\n/).forEach(line =>  {

    const middle = line.length / 2;
    const firstCompartment = line.substring(0,middle).split('');
    const secondCompartment = line.substring(middle).split('');
    // console.log('middle',middle);
    // console.log('firstCompartment',firstCompartment);
    // console.log('secondCompartment',secondCompartment);
    
    var dieZeile = [];
    for (var i = 0; i < firstCompartment.length; i++) { 
        if(secondCompartment.includes(firstCompartment[i])){
            if(dieZeile.indexOf(firstCompartment[i]) === -1){
             dieZeile.push(firstCompartment[i]); 
             inBoth.push(firstCompartment[i])
            }

        }
    }
    //highest.push(calculateMatchPoints(lineValues[0],lineValues[1]))
  });
  console.log('inBoth',inBoth);

  var sum =0;
  for (var i = 0; i < inBoth.length; i++) { 
  if(inBoth[i] == inBoth[i].toUpperCase()){
    //upper
    var index=charsUpper.indexOf(inBoth[i])+27;
    console.log('code',inBoth[i] + ' ' + index);
    sum +=index;
  } else if(inBoth[i] == inBoth[i].toLowerCase()){
    //lower
    var index=charsLower.indexOf(inBoth[i])+1;
    console.log('code',inBoth[i] + ' ' + index);
    sum +=index;
  }
  console.log('sum',sum);
}
  

  


  //console.log(highest.reduce((partialSum, a) => partialSum + a, 0));

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
