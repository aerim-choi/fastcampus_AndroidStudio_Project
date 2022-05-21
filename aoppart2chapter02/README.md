### 리스트를 사용하여 로또번호 추출

```kotlin
fun main() {

    val random = Random() //seed값 추가 하지 않아여 나노타임이 들어가서 같은 결과가 나오지 않음
    //리스트를 사용하여 로또번호 추출
    val list = mutableListOf<Int>()
    
    while(list.size < 6){
        val randomNumber = random.nextInt(45)+1//1~45사이의 랜덤값
        if(list.contains(randomNumber)){ //중복된 수 제거
            continue
        }
        list.add(randomNumber)
    }
    println(list)
    
}      

