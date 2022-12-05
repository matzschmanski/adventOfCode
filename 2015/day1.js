
import { readFile } from '../2022/util/util.js';

var input = readFile('/Users/geig006/Development/projects/priv/adventOfCode/2015/input/day1_1');

var floor =0;
var count =0;
input.split(/\r?\n/).forEach(line =>  {

  line.split('').forEach(character => {
    if(character==='('){
      floor++;
    }else if(character===')'){
      floor--;
    }
    count++;
    if(floor===-1){
      console.log('first hit floor', count);
    }
    
  });
    
  });
  console.log(floor);

