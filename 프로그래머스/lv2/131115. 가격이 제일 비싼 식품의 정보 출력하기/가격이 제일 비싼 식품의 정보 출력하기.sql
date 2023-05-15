-- 코드를 입력하세요
SELECT *
FROM FOOD_PRODUCT
ORDER BY PRICE DESC
LIMIT 1

# set @mx := (select max(price) from food_product);

# select *
# from food_product
# where price = @mx