#include <string>
#include <vector>

using namespace std;

string solution(string number, int k) {
    int cnt = 0;
    if(k == number.size()-1)
        for(int i=9; i>0; i--)
            for(int j=0; j<number.size(); j++)
                if(number[j]-48 == i)
                    return to_string(i);
    if(k == 1)
        for(int i=0; i<=9; i++)
            for(int j=0; j<number.size(); j++)
                if(number[j] - 48 == i)
                    return number.erase(j,1);
    while(true) {
        for(int i=0; i<number.size(); i++) {
            if(number[i] < number[i+1]) {
                number.erase(i,1);
                cnt++;
                break;
            }
        }
        if(cnt == k)
            return number;
    }
}