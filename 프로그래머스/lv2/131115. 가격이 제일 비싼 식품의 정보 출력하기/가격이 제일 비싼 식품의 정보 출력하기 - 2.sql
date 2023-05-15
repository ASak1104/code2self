-- 코드를 입력하세요
set @mx := (select max(price) from food_product);

select *
from food_product
where price = @mx