abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val pattern = "(7|8)[0-9]{10}$".toRegex()
        value?.let {
            if(it.length != 11)
                return listOf(ErrorCode.INVALID_LENGTH)
            if(!it.matches(pattern))
                return listOf(ErrorCode.INVALID_CHARACTER)
        } ?: return listOf(ErrorCode.CANNOT_BE_EMPTY)
        return emptyList()
    }
}

class NameAndSurnameValidator : Validator<String> () {
    override fun validate(value: String?): List<ErrorCode> {
        val pattern = "[A-Я][а-я]+".toRegex()
        value.let {
            if(it.isNullOrEmpty())
                return listOf(ErrorCode.CANNOT_BE_EMPTY)
            if(it.length > 16)
                return listOf(ErrorCode.INVALID_LENGTH)
            if(!it.matches(pattern))
                return listOf(ErrorCode.INVALID_CHARACTER)
        }
        return emptyList()
    }
}

class EmailValidator : Validator<String> () {
    override fun validate(value: String?): List<ErrorCode> {
        val pattern = "[\\w_\\.]+@\\w+(\\.[A-Za-z]{2,3})$".toRegex()
        value.let {
            if(it.isNullOrEmpty())
                return listOf(ErrorCode.CANNOT_BE_EMPTY)
            if(it.length > 32)
                return listOf(ErrorCode.INVALID_LENGTH)
            if(!it.matches(pattern))
                return listOf(ErrorCode.INVALID_CHARACTER)
        }
        return emptyList()
    }
}

class SnilsValidator : Validator<String> () {
    override fun validate(value: String?): List<ErrorCode> {
        val pattern = "\\d{11}".toRegex()

        value?.let {
            if(it.length != 11)
                return listOf(ErrorCode.INVALID_LENGTH)
            if(!it.matches(pattern))
                return listOf(ErrorCode.INVALID_CHARACTER)

            var sum = 0
            for(i in 0 .. it.lastIndex - 2) {
                sum += (it[i] - '0') * (9 - i)
            }
            val controlNumber = it.substring(9, 11)
            var expectedControlNumber = "00"
            if(sum > 101) {
                val mod = sum % 101
                expectedControlNumber = if(mod.toString().length < 2) "0$mod" else "$mod"
            }
            else if(sum < 100) {
                expectedControlNumber = if(sum.toString().length < 2) "0$sum" else "$sum"
            }
            if(controlNumber != expectedControlNumber)
                return listOf(ErrorCode.INVALID_SNILS_NUMBER)
        } ?: return listOf(ErrorCode.CANNOT_BE_EMPTY)
        return emptyList()
    }
}