-- 코드를 입력하세요
SELECT ANIMAL_ID, I.NAME
FROM ANIMAL_INS I JOIN ANIMAL_OUTS O USING (ANIMAL_ID)
ORDER BY O.DATETIME - I.DATETIME DESC
LIMIT 2