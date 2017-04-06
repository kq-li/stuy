var revenue = d3.select("#revenue");
var spend = d3.select("#spend");
var deficit = d3.select("#deficit");
var gdp = d3.select("#gdp");

var years = [2015, 2016]
var info = [{
    year: 2015,
    revenue: 2.96,
    spending: 3.36,
    deficit: 0.399,
    gdp: 16.2
}, {
    year: 2016,
    revenue: 2.99,
    spending: 3.54,
    deficit: 0.552,
    gdp: 16.5
}];

var revEnter = revenue.selectAll('div')
    .data(info).enter().append('div').attr('class', 'data');

revEnter.append('div')
    .attr('class', 'label')
    .text((d) => d.year);

revEnter.append('div')
    .attr('class', 'bar')
    .transition()
    .duration(1000)
    .style('width', (d) => d.revenue * 75  + 'px')
    .text((d) => d.revenue);

var spendEnter = spend.selectAll('div')
    .data(info).enter().append('div').attr('class', 'data');

spendEnter.append('div')
    .attr('class', 'label')
    .text((d) => d.year);

spendEnter.append('div')
    .attr('class', 'bar')
    .transition()
    .duration(1000)
    .style('width', (d) => d.spending * 75  + 'px')
    .text((d) => d.spending);

var deficitEnter = deficit.selectAll('div')
    .data(info).enter().append('div').attr('class', 'data');

deficitEnter.append('div')
    .attr('class', 'label')
    .text((d) => d.year);

deficitEnter.append('div')
    .attr('class', 'bar')
    .transition()
    .duration(1000)
    .style('width', (d) => d.deficit * 75 + 'px')
    .text((d) => d.deficit);

var gdpEnter = gdp.selectAll('div')
    .data(info).enter().append('div').attr('class', 'data');

gdpEnter.append('div')
    .attr('class', 'label')
    .text((d) => d.year);

gdpEnter.append('div')
    .attr('class', 'bar')
    .transition()
    .duration(1000)
    .style('width', (d) => d.gdp * 75 + 'px')
    .text((d) => d.gdp);

//spend.selectAll('div')
    //.data(info2)
    //.enter()
    //.append('div')
    //.style('width', () => 0)
    //.transition()
    //.duration(1000)
    //.style('width', (d) => d * 30 + 'px')
    //.text((d) => d);

//def.selectAll('div')
    //.data(info3)
    //.enter()
    //.append('div')
    //.style('width', () => 0)
    //.transition()
    //.duration(1000)
    //.style('width', (d) => d * 30 + 'px')
    //.text((d) => d);

//gdp.selectAll('div')
    //.data(info4)
    //.enter()
    //.append('div')
    //.style('width', () => 0)
    //.transition()
    //.duration(1000)
    //.style('width', (d) => d * 30 + 'px')
    //.text((d) => d);
