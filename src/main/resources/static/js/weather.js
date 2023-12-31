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
        $('.weather-txt').append(getTextDescription(weatherResult.weather[0].icon));
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

function getTextDescription(description) {
    let txt;
    switch (description) {
        case '01d':
        case '01n':
            txt = '맑음';
            break;
        case '02d':
        case '02n':
            txt = '구름 약간';
            break;
        case '03d':
        case '03n':
            txt = '흐림';
            break;
        case '04d':
        case '04n':
            txt = '구름 없음';
            break;
        case '10d':
        case '10n':
            txt = '소나기';
            break;
        case '11d':
        case '11n':
            txt = '천둥번개';
            break;
        case '13d':
        case '13n':
            txt = '눈';
            break;
        case '50d':
        case '50n':
            txt = '안개';
            break;
    }
    return txt;
}

getLocation();