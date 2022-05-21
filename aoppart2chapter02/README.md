## 로또 번호 추첨기
#### 0~5개까지 수동 선택 가능하도록 구현하기
#### 수동선택한 번호를 제외한 나머지 번호는 랜덤으로 표시하기

![image](https://user-images.githubusercontent.com/80438964/169640238-536d1d83-04bc-478d-818c-07cadaeffd24.png)

#### Layout 그리는법 
+ ConstraintLayout 사용하기
+ NumberPicker의 속성들과 사용하는법
+ TextView의 속성들과 사용하는법
+ Button 사용하는법 

#### Shape Drawble 사용하기

#### Kotlin 문법
+ apply : 초기화 및 미리 데이터 넣을 때 사용
+ When : backgroundcolor 적용할때 사용
+ Random 
+ Collection(Set,List)
+ 람다함수

#### 1. List 를 사용하여 로또번호 추출

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

#### 2. Set를 사용하여 로또번호 추출
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

## 실행영상
![Android-Emulator-aop-part2-chapter02-2022-05-21-17-00-40](https://user-images.githubusercontent.com/80438964/169642221-3786e673-15fb-4c37-a1c8-8611c21a7a11.gif)

