#### 1. 리스트를 사용하여 로또번호 추출

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
```

#### 2. Set 사용하여 로또번호 추출
```kotlin
fun main() {
    
    val random = Random() //seed값 추가 하지 않아여 나노타임이 들어가서 같은 결과가 나오지 않음
    //Set를 사용하여 로또번호 추출
    val numberSet = mutableSetOf<Int>()
    
    while(numberSet.size < 6){
        val randomNumber = random.nextInt(45)+1//1~45사이의 랜덤값
        numberSet.add(randomNumber)
    }
    println(numberSet)
}                       
```
#### 3. shuffle, apply 사용하여 로또 번호 추출
```kotlin
fun main() {
    
    val random = Random() //seed값 추가 하지 않아여 나노타임이 들어가서 같은 결과가 나오지 않음
    //Set를 사용하여 로또번호 추출
    val list = mutableListOf<Int>().apply{ //apply는 초기화 하는 구문에 많이 사용하게 될것이다.
        for(i in 1..45){
            this.add(i)
        }
    }
    
    list.shuffle() //리스트 안에 있는 값들이 랜덤으로 섞임
    
    println(list.subList(0,6)) //0~6번째 인덱스 전까지 자른다.
}                       
```
