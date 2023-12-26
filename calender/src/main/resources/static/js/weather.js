// 지역 추가 시 https://openweathermap.org/city/1897000 접속 후 검색
const API_KEY="17be97eede6573f3de9cc7b23db3d280";
const SI_HEUNG = 'Siheung-si';
const SEONG_NAM = 'Seongnam-si';
const url = 'https://api.openweathermap.org/data/2.5/weather?q=' + SI_HEUNG + ',kr&appid=' + API_KEY + '&units=metric'

$.getJSON(url,
    function (WeatherResult) {
        setAreaName();
        $('.SeoulNowtemp').append(WeatherResult.main.temp);

        var weathericonUrl =
            '<img src= "http://openweathermap.org/img/wn/'
            + WeatherResult.weather[0].icon +
            '.png" alt="' + WeatherResult.weather[0].description + '"/>'

        $('.SeoulIcon').html(weathericonUrl);
    });

function setAreaName() {
    if(url.includes(SEONG_NAM)) {
        $('.position').html('성남시')
        return;
    }
    if(url.includes(SI_HEUNG)) {
        $('.position').html('시흥시')
        return;
    }
}