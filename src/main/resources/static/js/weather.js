const API_KEY="17be97eede6573f3de9cc7b23db3d280";

function getLocation() {
    if(navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            let lat = position.coords.latitude;
            let lon = position.coords.longitude;
            getWeather(lat, lon)
        });
    }
}

function getWeather(lat, lon) {
    const url = 'https://api.openweathermap.org/data/2.5/weather?lat=' + lat + '&lon=' + lon + '&appid=' + API_KEY;

    $.getJSON(url, function (weatherResult) {
        let temp = (weatherResult.main.temp - 273.15).toFixed(2);
        setAreaName(weatherResult.name);
        $('.temperatures').append(temp + ' °C');
        var weathericonUrl =
            '<img src= "http://openweathermap.org/img/wn/'
            + weatherResult.weather[0].icon +
            '@2x.png" alt="' + weatherResult.weather[0].description + '"/>'
        $('.weather-ico').html(weathericonUrl);
    });
}

function setAreaName(name) {
    switch (name) {
        case 'Seongnam-si':
            $('.position').html('경기도 성남시')
            break;
        case 'Siheung-si':
            $('.position').html('경기도 시흥시')
            break;
        default:
            $('.position').html(name)
            break;
    }
}

getLocation();