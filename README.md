# 다양한 API 테스트

## 1. OpenWeatherMap API 추가
원하는 도시의 현재 날씨 요청

### 경로
> /weather/city={도시명}
### application.properties
```
openweathermap.baseUrl=https://api.openweathermap.org/data/2.5
openweathermap.apiKey="MY API KEY"
openweathermap.lang=kr
```

## 2. GoogleMap API 추가
1. Maps JavaScript API
 - 웹페이지에 지도 표시
2. places API
- 위치 및 장소와 관련된 데이터를 제공

### 경로
> /map

### application.properties
```
google.api.key="MY API KEY"
```