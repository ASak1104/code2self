-- 코드를 입력하세요
SELECT ANIMAL_ID, I.NAME AS NAME
FROM ANIMAL_INS I JOIN ANIMAL_OUTS O USING (ANIMAL_ID)
WHERE I.DATETIME > O.DATETIME
ORDER BY I.DATETIME