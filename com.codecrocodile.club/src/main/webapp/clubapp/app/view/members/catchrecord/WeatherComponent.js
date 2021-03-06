Ext.define('GF.view.members.catchrecord.WeatherComponent', {
   extend: 'Ext.Component',
   xtype: 'weathercomponent',
   autoEl: 'ul',
   cls: 'outlook_div',
   data: { 
       tempAndWeatherDescription: '',
       weatherImage: '',
       windDescription: '',
       precipMM: '',
       cloudcover: '',
       humidity: '',
       visibility: ''
   },
  
   tpl:[
       '<li class="outlook_box1">',
           '<span style="display: block; text-align: center;">{tempAndWeatherDescription}</span>',
           '<img style="display: block; margin-left: auto; margin-right: auto; width: 150px; height: 150px; padding: 5px" alt="Weather Image" src="{weatherImage}">',
       '</li>',
       '<li class="outlook_box2">',
           '<div>',
               '<div class="outlook_left">Wind:</div>',
               '<div class="outlook_right">{windDescription}</div>',
               '<div class="outlookclear"></div>',
           '</div>',
           '<div>',
               '<div class="outlook_left">Cloud Cover:</div>',
               '<div class="outlook_right">{cloudcover} %</div>',
               '<div class="outlookclear"></div>',
           '</div>',
           '<div>',
               '<div class="outlook_left">Precipitation:</div>',
               '<div class="outlook_right">{precipMM} mm</div>',
               '<div class="outlookclear"></div>',
           '</div>',
           '<div>',
               '<div class="outlook_left">Humidity:</div>',
               '<div class="outlook_right">{humidity} %</div>',
               '<div class="outlookclear"></div>',
           '</div>',
           '<div>',
               '<div class="outlook_left">Visibility:</div>',
               '<div class="outlook_right">{visibility} km</div>',
               '<div class="outlookclear"></div>',
           '</div>',
       '</li>'
   ]
    
});
