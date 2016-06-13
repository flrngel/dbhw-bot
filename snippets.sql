CREATE FULLTEXT INDEX ft_index ON Items (title,description,keywords) WITH PARSER mecab;
