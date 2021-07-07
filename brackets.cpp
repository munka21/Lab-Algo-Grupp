#include <iostream>


int main (){
  
  int open = 0;
  int q = 0;
  char c;
    while (std::cin >> c){
    
    
    //std::cout << c <<std::endl;

    if(c == '\n' || c == '\r\n'){
       break;
    }
    else if (c == '(') {
          
        open++;
    }
    else if (c == '?'){
        
      if(open == 0){
          
          open ++;
          
      }
      else{
          open--;
          q++;
      }
        
    }
    else if (c == ')') {
            
        if(open>0){
              
            open --;
        }
        else if(q > 0){
            q--;
            open++;
        }
        else {
            std::cout << 0 <<std::endl;
            return 0;
        }
    }
      //std::cout << "open: "<<open <<std::endl;
      /std::cout <<"q: "<< q <<std::endl;
  }
    if(open == 0){
        std::cout << 1 <<std::endl;
        return 0;
    }
    else{
        std::cout << 0 <<std::endl;
    }
}


