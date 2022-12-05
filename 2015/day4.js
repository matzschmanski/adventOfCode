import { md5 } from '../util/util.js';
var input = 'ckczppom';
var begin = '000000';
var number =0;

var found =false;

while(!found){
    var hash = md5(input+number);
    console.log('calculating ',input+number);
    
    if(hash.startsWith(begin)){
        console.log(hash);
        found=true;
        console.log('answer: ',number);
    }
    
    number++;
    
}





