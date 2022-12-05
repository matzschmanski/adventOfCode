import { readFile } from '../2022/util/util.js';

var input = readFile('/Users/geig006/Development/projects/priv/adventOfCode/2015/input/day3_1');

var xSanta = 0;
var ySanta = 0;
var xRobot =0;
var yRobot=0;
const coords = new Map();
coords.set('0:0', 1);
var i=0;
input.split(/\r?\n/).forEach(line => {
  line.split('').forEach(splitChar =>{
    switch(splitChar) {
      case "^":
        if(i%2==0){
          ySanta++;
        }else{
          yRobot++;
        }
        break;
      case "v":
        if(i%2==0){
          ySanta--;
        }else{
          yRobot--;
        }
        break;
      case "<":
        if(i%2==0){
          xSanta--;
        }else{
          xRobot--;
        }
        break;
      case ">":
        if(i%2==0){
          xSanta++;
        }else{
          xRobot++;
        }
        break;
      default:
        console.log('?!');
    }
    if(i%2==0){
      var positionSanta = xSanta+':'+ySanta;
      if(coords.get(positionSanta)==null){
        coords.set(positionSanta, 1);
      }else{
        coords.set(positionSanta, coords.get(positionSanta)+1);
        
      }
    }else{
      var positionRobot = xRobot+':'+yRobot;
      if(coords.get(positionRobot)==null){
        coords.set(positionRobot, 1);
      }else{
        coords.set(positionRobot, coords.get(positionRobot)+1);
        
      }
    }
    i++;
  });
  // console.log(coords);
  console.log(coords.size);
  

});



